package com.hspedu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Adam
 * @create 2023-11-29-14:54
 * @description:
 */
@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {

        //启动springboot应用程序/项目
        //老师提出问题: 当我们执行run方法时，怎么就启动我们的内置的tomcat?
        //在分析run方法的底层机制的基础上，我们自己尝试实现
        ConfigurableApplicationContext ioc =
                SpringApplication.run(MainApp.class, args);

        /**
         *  这里我们开始Debug SpringApplication.run()
         *  1. SpringApplication.java
         *  public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
         * 		return run(new Class<?>[] { primarySource }, args);
         *   }
         *
         *  2.SpringApplication.java : 创建返回 ConfigurableApplicationContext对象
         *  public static ConfigurableApplicationContext run(Class<?>[] primarySources, String[] args) {
         * 		return new SpringApplication(primarySources).run(args);
         *        }
         *
         *  3. SpringApplication.java
         *
         *  public ConfigurableApplicationContext run(String... args) {
         * 		StopWatch stopWatch = new StopWatch();
         * 		stopWatch.start();
         * 		DefaultBootstrapContext bootstrapContext = createBootstrapContext();
         * 		ConfigurableApplicationContext context = null;
         * 		configureHeadlessProperty();
         * 		SpringApplicationRunListeners listeners = getRunListeners(args);
         * 		listeners.starting(bootstrapContext, this.mainApplicationClass);
         * 		try {
         * 			ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
         * 			ConfigurableEnvironment environment = prepareEnvironment(listeners, bootstrapContext, applicationArguments);
         * 			configureIgnoreBeanInfo(environment);
         * 			Banner printedBanner = printBanner(environment);
         * 			context = createApplicationContext(); //严重分析: 创建容器
         * 			context.setApplicationStartup(this.applicationStartup);
         * 			prepareContext(bootstrapContext, context, environment, listeners, applicationArguments, printedBanner);
         * 			refreshContext(context); //严重分析: 刷新应用程序上下文,比如 初始化默认设置/注入相关Bean/启动tomcat
         * 			afterRefresh(context, applicationArguments);
         * 			stopWatch.stop();
         * 			if (this.logStartupInfo) {
         * 				new StartupInfoLogger(this.mainApplicationClass).logStarted(getApplicationLog(), stopWatch);
         *                        }
         * 			listeners.started(context);
         * 			callRunners(context, applicationArguments);* 		}
         * 		catch (Throwable ex) {
         * 			handleRunFailure(context, ex, listeners);
         * 			throw new IllegalStateException(ex);
         *        }
         *
         * 		try {
         * 			listeners.running(context);
         *        }
         * 		catch (Throwable ex) {
         * 			handleRunFailure(context, ex, null);
         * 			throw new IllegalStateException(ex);
         *        }
         * 		return context;
         *    }
         *
         * 	4. SpringApplication.java ： 容器类型很多，会根据你的this.webApplicationType创建对应的容器
         * 	默认 this.webApplicationType 是 SERVLET 也就是web容器/可以处理servlet
         * 	protected ConfigurableApplicationContext createApplicationContext() {
         * 		return this.applicationContextFactory.create(this.webApplicationType);
         *        }
         *
         *  5. ApplicationContextFactory.java
         *
         *  ApplicationContextFactory DEFAULT = (webApplicationType) -> {
         * 		try {
         * 			switch (webApplicationType) {
         * 			case SERVLET://默认是进入到这个分支 ，返回AnnotationConfigServletWebServerApplicationContext容器
         * 				return new AnnotationConfigServletWebServerApplicationContext();
         * 			case REACTIVE:
         * 				return new AnnotationConfigReactiveWebServerApplicationContext();
         * 			default:
         * 				return new AnnotationConfigApplicationContext();
         *                        }* 		}
         * 		catch (Exception ex) {
         * 			throw new IllegalStateException("Unable create a default ApplicationContext instance, "
         * 					+ "you may need a custom ApplicationContextFactory", ex);
         *        }
         *    };
         *
         * 	6. SpringApplication.java
         * 	private void refreshContext(ConfigurableApplicationContext context) {
         * 		if (this.registerShutdownHook) {
         * 			shutdownHook.registerApplicationContext(context);
         *                }
         * 		refresh(context); //严重分析，真正执行相关任务
         *  }
         *
         *  7. SpringApplication.java
         *  protected void refresh(ConfigurableApplicationContext applicationContext) {
         * 		applicationContext.refresh();
         *    }
         *
         *
         *  8. ServletWebServerApplicationContext.java
         *  @Override
         *        public final void refresh() throws BeansException, IllegalStateException {
         * 		try {
         * 			super.refresh();//分析这个方法
         *        }
         * 		catch (RuntimeException ex) {
         * 			WebServer webServer = this.webServer;
         * 			if (webServer != null) {
         * 				webServer.stop();
         *            }
         * 			throw ex;
         *        }
         *    }
         *
         * 9. AbstractApplicationContext.java
         *
         * @Override
         *        public void refresh() throws BeansException, IllegalStateException {
         * 		synchronized (this.startupShutdownMonitor) {
         * 			StartupStep contextRefresh = this.applicationStartup.start("spring.context.refresh");
         *
         * 			// Prepare this context for refreshing.
         * 			prepareRefresh();
         *
         * 			// Tell the subclass to refresh the internal bean factory.
         * 			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
         *
         * 			// Prepare the bean factory for use in this context.
         * 			prepareBeanFactory(beanFactory);
         *
         * 			try {
         * 				// Allows post-processing of the bean factory in context subclasses.
         * 				postProcessBeanFactory(beanFactory);
         *
         * 				StartupStep beanPostProcess = this.applicationStartup.start("spring.context.beans.post-process");
         * 				// Invoke factory processors registered as beans in the context.
         * 				invokeBeanFactoryPostProcessors(beanFactory);
         *
         * 				// Register bean processors that intercept bean creation.
         * 				registerBeanPostProcessors(beanFactory);
         * 				beanPostProcess.end();
         *
         * 				// Initialize message source for this context.
         * 				initMessageSource();
         *
         * 				// Initialize event multicaster for this context.
         * 				initApplicationEventMulticaster();
         *
         * 				// Initialize other special beans in specific context subclasses.
         * 				onRefresh(); //严重分析，当父类完成通用的工作后，再重新动态绑定机制回到子类
         *
         * 				// Check for listener beans and register them.
         * 				registerListeners();
         *
         * 				// Instantiate all remaining (non-lazy-init) singletons.
         * 				finishBeanFactoryInitialization(beanFactory);
         *
         * 				// Last step: publish corresponding event.
         * 				finishRefresh();
         *            }
         *
         * 			catch (BeansException ex) {
         * 				if (logger.isWarnEnabled()) {
         * 					logger.warn("Exception encountered during context initialization - " +
         * 							"cancelling refresh attempt: " + ex);
         *                }
         *
         * 				// Destroy already created singletons to avoid dangling resources.
         * 				destroyBeans();
         *
         * 				// Reset 'active' flag.
         * 				cancelRefresh(ex);
         *
         * 				// Propagate exception to caller.
         * 				throw ex;
         *            }
         *
         * 			finally {
         * 				// Reset common introspection caches in Spring's core, since we
         * 				// might not ever need metadata for singleton beans anymore...
         * 				resetCommonCaches();
         * 				contextRefresh.end();
         *            }
         *        }
         *    }
         *  10. ServletWebServerApplicationContext.java
         *  @Override
         *    protected void onRefresh() {
         * 		super.onRefresh();
         * 		try {
         * 			createWebServer();//看到胜利的曙光,创建webserver 可以理解成会创建指定web服务-Tomcat
         *                }
         * 		catch (Throwable ex) {
         * 			throw new ApplicationContextException("Unable to start web server", ex);
         *        }    * 	}
         *   11. ServletWebServerApplicationContext.java
         *
         *   private void createWebServer() {
         * 		WebServer webServer = this.webServer;
         * 		ServletContext servletContext = getServletContext();
         * 		if (webServer == null && servletContext == null) {
         * 			StartupStep createWebServer = this.getApplicationStartup().start("spring.boot.webserver.create");
         * 			ServletWebServerFactory factory = getWebServerFactory();
         * 			createWebServer.tag("factory", factory.getClass().toString());
         * 			this.webServer = factory.getWebServer(getSelfInitializer());//严重分析,使用TomcatServletWebServerFactory 创建一个TomcatWebServer
         * 			createWebServer.end();
         * 			getBeanFactory().registerSingleton("webServerGracefulShutdown",
         * 					new WebServerGracefulShutdownLifecycle(this.webServer));
         * 			getBeanFactory().registerSingleton("webServerStartStop",
         * 					new WebServerStartStopLifecycle(this, this.webServer));
         *                }
         * 		else if (servletContext != null) {
         * 			try {
         * 				getSelfInitializer().onStartup(servletContext);
         *            }
         * 			catch (ServletException ex) {
         * 				throw new ApplicationContextException("Cannot initialize servlet context", ex);
         *            }
         *        }
         * 		initPropertySources();    * 	}
         *
         * 	12. TomcatServletWebServerFactory.java 会创建Tomcat 并启动Tomcat
         *
         *        @Override
         *    public WebServer getWebServer(ServletContextInitializer... initializers) {
         * 		if (this.disableMBeanRegistry) {
         * 			Registry.disableRegistry();
         *        }
         * 		Tomcat tomcat = new Tomcat();//创建了Tomcat对象
         * 		File baseDir = (this.baseDirectory != null) ? this.baseDirectory : createTempDir("tomcat");
         * 	    //做了一系列的设置
         * 		tomcat.setBaseDir(baseDir.getAbsolutePath());
         *
         * 		Connector connector = new Connector(this.protocol);
         * 		connector.setThrowOnFailure(true);
         * 		tomcat.getService().addConnector(connector);
         * 		customizeConnector(connector);
         * 		tomcat.setConnector(connector);
         * 		tomcat.getHost().setAutoDeploy(false);
         * 		configureEngine(tomcat.getEngine());
         * 		for (Connector additionalConnector : this.additionalTomcatConnectors) {
         * 			tomcat.getService().addConnector(additionalConnector);
         *        }
         * 		prepareContext(tomcat.getHost(), initializers);
         * 		return getTomcatWebServer(tomcat); //严重分析该方法.
         *    }
         *
         *    13. TomcatServletWebServerFactory.java , 这里做了校验创建 TomcatWebServer
         *    protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
         * 		return new TomcatWebServer(tomcat, getPort() >= 0, getShutdown());
         *        }
         *    14. TomcatServletWebServerFactory.java
         *    public TomcatWebServer(Tomcat tomcat, boolean autoStart, Shutdown shutdown) {
         * 		Assert.notNull(tomcat, "Tomcat Server must not be null");
         * 		this.tomcat = tomcat;
         * 		this.autoStart = autoStart;
         * 		this.gracefulShutdown = (shutdown == Shutdown.GRACEFUL) ? new GracefulShutdown(tomcat) : null;
         * 		initialize();//分析这个方法.
         *        }
         *    15.TomcatServletWebServerFactory.java
         *
         *    private void initialize() throws WebServerException {
         * 		logger.info("Tomcat initialized with port(s): " + getPortsDescription(false));
         * 		synchronized (this.monitor) {
         * 			try {
         * 				addInstanceIdToEngineName();
         *
         * 				Context context = findContext();
         * 				context.addLifecycleListener((event) -> {
         * 					if (context.equals(event.getSource()) && Lifecycle.START_EVENT.equals(event.getType())) {
         * 						// Remove service connectors so that protocol binding doesn't
         * 						// happen when the service is started.
         * 						removeServiceConnectors();
         *                                        }                * 				});
         *
         * 				// Start the server to trigger initialization listeners
         * 				this.tomcat.start(); //启动Tomcat
         *
         * 				// We can re-throw failure exception directly in the main thread
         * 				rethrowDeferredStartupExceptions();
         *
         * 				try {
         * 					ContextBindings.bindClassLoader(context, context.getNamingToken(), getClass().getClassLoader());
         *                }
         * 				catch (NamingException ex) {
         * 					// Naming is not enabled. Continue
         *                }
         *
         * 				// Unlike Jetty, all Tomcat threads are daemon threads. We create a
         * 				// blocking non-daemon to stop immediate shutdown
         * 				startDaemonAwaitThread();
         *            }
         * 			catch (Exception ex) {
         * 				stopSilently();
         * 				destroySilently();
         * 				throw new WebServerException("Unable to start embedded Tomcat", ex);
         *            }
         *        }
         *    }
         */
        System.out.println("hello ioc");

    }
}
