/*
 * Copyright (C) 2021 Dynamia Soluciones IT S.A.S - NIT 900302344-1
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

import mybookstore.domain.Book;
import mybookstore.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tools.dynamia.commons.StringUtils;
import tools.dynamia.domain.services.CrudService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class InitSampleDataCommandLinerRunner implements CommandLineRunner {

    @Autowired
    private CrudService crudService;


    @Override
    public void run(String... args) throws Exception {

        var novels = new Category("Novels")
                .add(new Category("Fantasy")) //subcategories
                .add(new Category("Scifi"))
                .add(new Category("Drama"));

        novels.save();

        var programming = new Category("Programming")
                .add(new Category("Java"))  //subcategories
                .add(new Category("Groovy"))
                .add(new Category("Kotlin"));

        programming.save();

        List.of("My First Pony", "100 years of drama", "My Best Friend", "The Little Prince").forEach(title -> {

            int random = new Random().nextInt(novels.getSubcategories().size() - 1);
            var book = new Book();
            book.setTitle(title);
            book.setCategory(novels.getSubcategories().get(random));
            book.setYear(1940);
            book.setBuyDate(new Date());
            book.setIsbn(StringUtils.randomString().toUpperCase());
            book.setSinopsys("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
            book.setPrice(BigDecimal.valueOf(33));
            crudService.save(book);
        });

        List.of("My First Programming", "Clean Code", "Design Patterns", "Scale to the cloud").forEach(title -> {
            int random = new Random().nextInt(programming.getSubcategories().size() - 1);
            var book = new Book();
            book.setTitle(title);
            book.setCategory(programming.getSubcategories().get(random));
            book.setYear(2020);
            book.setBuyDate(new Date());
            book.setIsbn(StringUtils.randomString().toUpperCase());
            book.setSinopsys("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
            book.setPrice(BigDecimal.valueOf(40));
            crudService.save(book);
        });

        System.out.println("Demo ready to run");
    }
}
