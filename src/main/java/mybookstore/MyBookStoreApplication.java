/*
 * Copyright (C) 2023 Dynamia Soluciones IT S.A.S - NIT 900302344-1
 * Colombia / South America
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mybookstore;

import mybookstore.domain.Category;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import tools.dynamia.app.Ehcache3CacheManager;
import tools.dynamia.domain.DefaultEntityReferenceRepository;
import tools.dynamia.domain.EntityReferenceRepository;
import tools.dynamia.navigation.DefaultPageProvider;
import tools.dynamia.ui.icons.IconsProvider;
import tools.dynamia.zk.ui.ZIconsProvider;

@SpringBootApplication
@EntityScan({"mybookstore", "tools.dynamia"})
@EnableCaching
@EnableScheduling
public class MyBookStoreApplication { //<1>


    public static void main(String[] args) {
        SpringApplication.run(MyBookStoreApplication.class, args); //<2>
    }


    @Bean
    public CacheManager cacheManager() {
        return new Ehcache3CacheManager();
    }

    @Bean
    public EntityReferenceRepository<Long> categoriesReferenceRepository() {
        return new DefaultEntityReferenceRepository<>(Category.class, "name");
    }

    @Bean
    public DefaultPageProvider defaultPageProvider() {
        return () -> "library/books";
    }

    @Bean
    public IconsProvider iconsProvider(){
        return new ZIconsProvider();
    }

}
