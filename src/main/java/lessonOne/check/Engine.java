package lessonOne.check;

import lessonOne.check.abstraction.Launchable;

public class Engine implements Launchable {
    private boolean isLaunched;

    public Engine(){
        this.isLaunched = false;
    }

    @Override
    public void launch() {
        this.isLaunched = true;
    }

    public void stop() {
        this.isLaunched = false;
    }
    public boolean getStatus(){
        return isLaunched;
    }

}
