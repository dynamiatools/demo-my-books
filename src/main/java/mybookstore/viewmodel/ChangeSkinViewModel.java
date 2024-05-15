package mybookstore.viewmodel;

import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import tools.dynamia.app.SessionApplicationTemplate;
import tools.dynamia.ui.UIMessages;
import tools.dynamia.zk.ZKAppConfiguration;

public class ChangeSkinViewModel {


    private String skin;

    @Init
    public void init() {
        skin = SessionApplicationTemplate.get().getSkin().getId();
    }


    @GlobalCommand
    public void apply() {
        if (skin != null) {
            ZKAppConfiguration.updateSkin(skin);
            UIMessages.showMessage("Reloading..");
        }
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }
}
