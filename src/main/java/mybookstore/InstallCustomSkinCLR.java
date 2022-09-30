package mybookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import tools.dynamia.app.template.Skin;
import tools.dynamia.integration.sterotypes.Provider;
import tools.dynamia.themes.dynamical.DynamicalTemplate;

@Provider
public class InstallCustomSkinCLR implements CommandLineRunner {

    @Autowired
    private DynamicalTemplate template;

    @Override
    public void run(String... args) throws Exception {
        var custom = new Skin("books", "Books Style", "skin-green-min.css", "Custom skin");
        custom.setCustomLayout(true);
        custom.setLayoutView("books/template");
        template.installSkin(custom);
    }
}
