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

package mybookstore.providers;

import mybookstore.domain.Book;
import mybookstore.domain.Category;
import mybookstore.domain.Customer;
import mybookstore.domain.Invoice;
import tools.dynamia.crud.CrudPage;
import tools.dynamia.integration.sterotypes.Provider;
import tools.dynamia.navigation.Module;
import tools.dynamia.navigation.ModuleProvider;
import tools.dynamia.navigation.Page;
import tools.dynamia.navigation.PageGroup;

@Provider
public class MyBookStoreModuleProvider implements ModuleProvider { // <1>

    @Override
    public Module getModule() { //<2>

        var mod = new Module("library", "Library") //<3>
                .icon("book")
                .description("my books library")
                .position(0)
                .addPage(new CrudPage("books", "Books", Book.class))
                .addPage(new CrudPage("categories", "Categories", Category.class).icon("tree"))
                .addPage(new CrudPage("customers", "Customers", Customer.class).icon("people"))
                .addPage(new CrudPage("invoices", "Invoices", Invoice.class));


        var examples = new PageGroup("examples", "More Examples");
        mod.addPageGroup(examples);

        examples.addPage(new Page("components", "Custom Components", "classpath:/pages/custom-components.zul"));
        examples.addPage(new Page("vue", "Vue Example", "classpath:/pages/vue-integration.zul"));
        examples.addPage(new Page("html", "Simple Html File", "/file.html?v=1"));
        examples.addPage(new Page("mvvm", "Standard ZK MVVM", "classpath:/pages/standard-mvvm.zul"));
        examples.addPage(new Page("chartjs", "Charts for ZK", "classpath:/pages/chartjs.zul"));
        examples.addPage(new Page("aceditor", "Ace Code Editor", "classpath:/pages/aceditor.zul"));
        examples.addPage(new Page("signaturepad", "Signature Pad", "classpath:/pages/signaturepad.zul"));


        return mod;

    }
}
