package mybookstore.viewmodel;

import mybookstore.domain.Book;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ToServerCommand;
import tools.dynamia.domain.util.DomainUtils;
import tools.dynamia.integration.Containers;
import tools.dynamia.ui.UIMessages;
import tools.dynamia.zk.crud.actions.ViewDataAction;

@ToServerCommand({"hello","showBook"})
public class VueViewModel {

    @Command
    public void hello() {
        System.out.println("Command receive");
        UIMessages.showMessage("Hello Vue!");
    }

    @Command
    public void showBook(@BindingParam("bookId") Long bookId){
        var book = DomainUtils.lookupCrudService().find(Book.class,bookId);
        var viewAction = Containers.get().findObject(ViewDataAction.class);
        viewAction.view(book);
    }
}
