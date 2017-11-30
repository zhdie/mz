package com.deloitte.hk.boot;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import com.deloitte.hk.MBApp;

/**
 * 这个类的作用与在web.xml中配置负责初始化Spring应用上下文的监听器作用类似，
 * 只不过在这里不需要编写额外的XML文件了。
 * 如果要将最终的打包形式改为WAR的话，还需要对pom.xml文件进行修改，
 * 除了需要将packaging的值修改为war以外，还需要对依赖进行适当的配置
 * @author zhangdie
 *
 */
public class AppWarServlet extends SpringBootServletInitializer {

	@Override  
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {  
        return application.sources(MBApp.class);  
    }
}
