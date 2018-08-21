package com.server.scala.config

import java.util
import java.util.{Collections, HashMap, Map}

import javax.sql.DataSource
import org.aspectj.lang.annotation.Aspect
import org.springframework.aop.Advisor
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.{PlatformTransactionManager, TransactionDefinition}
import org.springframework.transaction.interceptor._

/**
  * @since 18-8-16 10:29 by jdk 1.8
  * @author qiwenshuai
  * @define AOP配置事务
  */
@Configuration
@Aspect
class TransactionManager {
  private val TX_METHOD_TIMEOUT = 50000
  private val AOP_POINTCUT_EXPRESSION = "execution(* com.server.*.service.*.*(..))"

  @Bean(Array("txManager")) def annotationDrivenTransactionManager(dataSource: DataSource) = new DataSourceTransactionManager(dataSource)

  // 事务的实现Advice
  @Bean def txAdvice(txManager: PlatformTransactionManager): TransactionInterceptor = {
    val source = new NameMatchTransactionAttributeSource
    /*不支持当前的事务,并且以非事务的方式运行,使用于查询一类*/
    val readOnlyTx = new RuleBasedTransactionAttribute
    readOnlyTx.setReadOnly(true)
    readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED)
    /*当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务*/
    val requiredTx = new RuleBasedTransactionAttribute
    requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(classOf[Exception])))
    requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED)
    requiredTx.setTimeout(TX_METHOD_TIMEOUT)
    val txMap = new util.HashMap[String, TransactionAttribute]
    txMap.put("add*", requiredTx)
    txMap.put("save*", requiredTx)
    txMap.put("insert*", requiredTx)
    txMap.put("update*", requiredTx)
    txMap.put("delete*", requiredTx)
    txMap.put("remove*", requiredTx)
    txMap.put("get*", readOnlyTx)
    txMap.put("query*", readOnlyTx)
    txMap.put("select*", readOnlyTx)
    txMap.put("find*",readOnlyTx)
    source.setNameMap(txMap)
    new TransactionInterceptor(txManager, source)
  }

  @Bean def txAdviceAdvisor(txAdvice: TransactionInterceptor): Advisor = {
    val pointcut = new AspectJExpressionPointcut
    pointcut.setExpression(AOP_POINTCUT_EXPRESSION)
    new DefaultPointcutAdvisor(pointcut, txAdvice)
  }

}
