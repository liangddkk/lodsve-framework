package lodsve.search.configs;

import lodsve.search.engine.SearchEngine;
import lodsve.search.engine.SolrSearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * solr配置.
 *
 * @author sunhao(sunhao.java@gmail.com)
 * @version V1.0, 2016/1/20 12:33
 */
@Configurable
@ComponentScan("lodsve.search")
public class SolrConfiguration {
    @Autowired
    private SearchProperties properties;

    @Bean
    public SearchEngine searchEngine() {
        SolrSearchEngine searchEngine = new SolrSearchEngine();
        searchEngine.setHtmlPrefix(properties.getPrefix());
        searchEngine.setHtmlSuffix(properties.getSuffix());
        searchEngine.setServer(properties.getServer());

        return searchEngine;
    }
}
