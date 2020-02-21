#SpringBoot学习笔记  

`@SpringBootApplication`:标注在主配置类上  
`@ConfigurationProperties(prefix="xxx")`:将配置文件(application.properties或application.yml)中xxx下所有属性与标注类中的属性一一映射，批量赋值
`@Value`:为单个属性赋值
`@PropertySource(value={"classpath:xxx.properties"})`:从指定的配置文件中获取值
`@Configuration`:标示配置类
YAML：
通过缩进表示层次
冒号后要加空格