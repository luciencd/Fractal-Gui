package basicCameraGUI;

public class Main{
    public static void main(String[] args){
        View view = new view();
        Model model = new model();


        //create jframe
        //frame.add(view.getUI());


        //make sure the view and model is fully initialized before letting the controller control them.
        Controller controller = new controller(view, model);


        //frame.setVisible(true);

    }
}