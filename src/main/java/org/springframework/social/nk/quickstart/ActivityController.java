package org.springframework.social.nk.quickstart;

import javax.inject.Inject;

import org.springframework.social.nk.api.ActivityOperations;
import org.springframework.social.nk.api.Nk;
import org.springframework.social.nk.api.impl.ActivityTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.nk.opensocial.model.NkActivity;
import pl.nk.opensocial.model.RestfulObject;

@Controller
@RequestMapping("/activities")
public class ActivityController {

    private final Nk nk;

    @Inject
    public ActivityController(Nk nk) {
        this.nk = nk;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model) {

        ActivityOperations<ActivityTemplate> oper = this.nk.activityOperations();
        
        RestfulObject<NkActivity> obj = oper.addActivityForAll("example activity " + System.currentTimeMillis());
        
        model.addAttribute("activity", obj.getEntry());
        
        return "activities";
    }

}
