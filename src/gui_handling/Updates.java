package gui_handling;

import javafx.concurrent.Task;

public class Updates extends Task<Integer>{


    @Override
    protected Integer call() throws Exception {
        return null;
    }

    @Override
    protected void updateTitle(String title) {
        super.updateTitle(title);
    }
}
