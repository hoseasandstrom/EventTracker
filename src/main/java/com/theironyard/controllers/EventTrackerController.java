package com.theironyard.controllers;

import com.theironyard.entities.Event;
import com.theironyard.entities.Message;
import com.theironyard.entities.User;
import com.theironyard.services.EventRepository;
import com.theironyard.services.MessageRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * Created by hoseasandstrom on 6/23/16.
 */
@Controller
public class EventTrackerController {
    @Autowired
    UserRepository users;

    @Autowired
    EventRepository events;

    @Autowired
    MessageRepository messages;

    //@Autowired
    //EventBlogRepository eventblogs;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");


        Iterable<Message> msgs = messages.findAll();
        model.addAttribute("msgs", msgs);



        model.addAttribute("username", username);
        model.addAttribute("events", events.findAll());
        model.addAttribute("now", LocalDateTime.now());
        return "home";

    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String username, String password) throws Exception {
        User user = users.findByName(username);
        if(user == null) {
            user = new User(username, PasswordStorage.createHash(password));
        }
        else if (!PasswordStorage.verifyPassword(password, user.getPassword())) {
            throw new Exception("Wrong password");
        }
        session.setAttribute("username", username);
        return "redirect:/";

    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";

    }

    @RequestMapping(path = "/create-event", method = RequestMethod.POST)
    public String createEvent(HttpSession session, String description, String time) {
        String username = (String) session.getAttribute("username");
        User user = users.findByName(username);
        Event event = new Event(description, LocalDateTime.parse(time), user);
        events.save(event);
        return "redirect:/";
    }

    @RequestMapping(path = "/addmessage", method = RequestMethod.POST)
    public String addmessage(String text, HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = users.findByName(username);
        Message message = new Message(text , user);
        messages.save(message);

        return "redirect:/";
    }
    @RequestMapping(path = "/deletemessage", method = RequestMethod.POST)
    public String deletemessage(HttpSession session, int id) {
        messages.delete(id);

        return "redirect:/";

    }
    @RequestMapping(path ="/editmessage", method = RequestMethod.GET)
    public  String editmessage(Model model, int id) {
        Message msg = messages.findById(id);
        model.addAttribute(msg);
        model.addAttribute("text");
        model.addAttribute("id");
        return "redirect:/";

    }
}
