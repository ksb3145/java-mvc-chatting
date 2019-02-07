package resources.view;

import controller.DispatcherController;
import dto.ModelAndView;
import util.PropUtil;

import java.awt.*;

public class HomeView implements IView {
    private DispatcherController dispatcherController = DispatcherController.getInstance();
    private static HomeView home = new HomeView();
    private InitializationView idx = InitializationView.getInstance();
    private Button loginButton, registerButton;
    private Label titleLabel, stateLabel;
    private TextField id, pw;

    public static HomeView getInstance() {
        return home;
    }

    private HomeView() {
    }

    @Override
    public void show(ModelAndView modelAndView) {
        init();
        stateLabel.setText(modelAndView.getText());
        addEventListener();
    }

    private void init() {
        dispatcherController = DispatcherController.getInstance();

        idx.frame.removeAll();
        idx.frame.setTitle("Login");
        idx.frame.setLayout(new GridLayout(6, 0));

        loginButton = new Button("Login");
        registerButton = new Button("RegisterView");
        id = new TextField();
        pw = new TextField();
        pw.setEchoChar('*');
        stateLabel = new Label();
        stateLabel.setAlignment(Label.CENTER);
        titleLabel = new Label(PropUtil.obj().get("config.msg.reqLogin"));
        titleLabel.setAlignment(Label.CENTER);

        idx.frame.add(titleLabel);
        idx.frame.add(id);
        idx.frame.add(pw);
        idx.frame.add(loginButton);
        idx.frame.add(registerButton);
        idx.frame.add(stateLabel);

        idx.frame.setVisible(true);
    }

    private void addEventListener() {
        ModelAndView modelAndView = new ModelAndView();
        loginButton.addActionListener(e -> {
            modelAndView.setUserName(id.getText());
            modelAndView.setPw(pw.getText());
            modelAndView.setUrl(PropUtil.obj().get("config.page.signIn"));
            dispatcherController.in(modelAndView);
        });
        registerButton.addActionListener(e -> {
            modelAndView.setUrl(PropUtil.obj().get("config.page.signUp"));
            dispatcherController.in(modelAndView);
        });
    }
}
