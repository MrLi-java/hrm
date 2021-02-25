package top.lmqstudy;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GeneratorCode {
    private static final String BUNDLE_CONFIG_NAME = "mybatis-plus-config";

    public static void main(String[] args) {
        //用来获取Mybatis-Plus.properties文件的配置信息
        ResourceBundle rb = ResourceBundle.getBundle(BUNDLE_CONFIG_NAME); //不要加后缀

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setFileOverride(false);
        gc.setActiveRecord(true);// 开启 activeRecord 模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(false);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setOpen(false);//生成代码后是否打开生成文件的文件夹窗口
        gc.setAuthor(rb.getString("author"));
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert());
        dsc.setDriverName(rb.getString("jdbc.driver"));
        dsc.setUsername(rb.getString("jdbc.user"));
        dsc.setPassword(rb.getString("jdbc.pwd"));
        dsc.setUrl(rb.getString("jdbc.url"));
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(rb.getString("parent"));  //设置根包
        pc.setController(rb.getString("controller")); //设置控制层的controller包名
        pc.setService(rb.getString("service")); //设置生成的service接口包名
        pc.setServiceImpl(rb.getString("service.impl"));

        pc.setEntity(rb.getString("domain"));
        pc.setMapper(rb.getString("mapper"));
        mpg.setPackageInfo(pc);

        //设置需要生成哪些模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setService(null); //不生成接口类

        mpg.setTemplate(templateConfig);

        //配置自定义模板生成的文件的保存路径
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
//                Map<String, Object> map = new HashMap<String, Object>();
//                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-rb");
//                this.setMap(map);
            }
        };

        //controller的输出配置
        focList.add(new FileOutConfig("/templates/controller.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //合并好的内容输出到哪儿？
                return rb.getString("OutputDirController") + rebulidToOutputPath(rb.getString("controller")) + tableInfo.getEntityName() + "Controller.java";
            }
        });
        //service接口的输出配置
        focList.add(new FileOutConfig("/templates/service.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return rb.getString("OutputDirServiceMapper") + rebulidToOutputPath(rb.getString("service")) + "I" + tableInfo.getEntityName() + "Service.java";
            }
        });
        focList.add(new FileOutConfig("/templates/serviceImpl.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return rb.getString("OutputDirServiceMapper") + rebulidToOutputPath(rb.getString("service.impl")) + tableInfo.getEntityName() + "ServiceImpl.java";
            }
        });
        focList.add(new FileOutConfig("/templates/mapper.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return rb.getString("OutputDirServiceMapper") + rebulidToOutputPath(rb.getString("mapper")) + tableInfo.getEntityName() + "Mapper.java";
            }
        });
        // 调整 domain 生成目录演示
        focList.add(new FileOutConfig("/templates/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return rb.getString("OutputDirBase") + rebulidToOutputPath(rb.getString("domain")) + tableInfo.getEntityName() + ".java";
            }
        });
        //query的输出配置
        focList.add(new FileOutConfig("/templates/query.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return rb.getString("OutputDirBase") + rebulidToOutputPath(rb.getString("query")) + tableInfo.getEntityName() + "Query.java";
            }
        });
        // 调整 xml 生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return rb.getString("OutputDirXml") + rebulidToOutputPath(rb.getString("mapper")) + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);//设置实体类支持lombok
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        strategy.setInclude(rb.getString("tableNames").split(","));
        //设置表的前缀
        String table_prefix = rb.getString("table_prefix");
        if (StringUtils.isNoneBlank(table_prefix)) {
            strategy.setTablePrefix(table_prefix.split(","));// 此处可以修改为您的表前缀
        }
        mpg.setStrategy(strategy);
        mpg.execute();
    }

    /**
     * 重新组装代码生成保存的controller/service/impl/mapper/domain具体包地址
     *
     * @return
     */
    private static String rebulidToOutputPath(String _package) {
        ResourceBundle rb = ResourceBundle.getBundle(BUNDLE_CONFIG_NAME); //不要加后缀
        String parent = rb.getString("parent") + "." + _package;
        String parentPath = StringUtils.replaceAll(parent, "\\.", "/");
        return "/" + parentPath + "/";
    }
}
