package service;

import domain.UserVO;
import dto.ModelAndView;
import util.PropUtil;
import dto.LoginDTO;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LoginService {
    private LoginService() {
    }

    private static LoginService loginService = new LoginService();

    public static LoginService getInstance() {
        return loginService;
    }

    public LoginDTO signIn(ModelAndView modelAndView) {
        modelAndView.setUrl("/home");
        return getPermission(modelAndView, "/signIn");
    }

    public LoginDTO signUp(ModelAndView modelAndView) {
        modelAndView.setUrl("/register");
        return getPermission(modelAndView, "/signUp");
    }

    private LoginDTO getPermission(ModelAndView modelAndView, String action) {
        LoginDTO sendDTO = new LoginDTO();
        sendDTO.setMessage(PropUtil.obj().get("config.msg.netErr"));
        sendDTO.setAccess(false);
        sendDTO.setUrl("/home");

        try {
            Socket socket = new Socket(PropUtil.obj().get("config.ntw.ip")
            		, Integer.parseInt(PropUtil.obj().get("config.ntw.port.client1")));
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            UserVO userVO = new UserVO();
            userVO.setUserName(modelAndView.getUserName());
            userVO.setPw(modelAndView.getPw());

            sendDTO.setAction(action);
            sendDTO.setUserVO(userVO);

            out.writeObject(sendDTO);
            out.flush();

            LoginDTO getDTO = (LoginDTO) in.readObject();

            getDTO.setUrl(modelAndView.getUrl());
            System.out.println("Login " + action + ": " + getDTO.isAccess() + ", " + getDTO.getMessage());

            return getDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendDTO;
    }
}
