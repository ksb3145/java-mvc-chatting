import controller.DispatcherController;
import dto.ModelAndView;
import util.PropUtil;

public class ChatTest {
    public static void main(String[] args) {
        DispatcherController dispatcherController = DispatcherController.getInstance();
        ModelAndView modelAndView = new ModelAndView(PropUtil.obj().get("page.index"));
        dispatcherController.in(modelAndView);
    }
}