package mybookstore.viewmodel;

import org.zkoss.bind.annotation.Command;

public class SignaturePadDemoViewModel {

    private String signature;

    @Command
    public void view(){
        System.out.println(signature);
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
