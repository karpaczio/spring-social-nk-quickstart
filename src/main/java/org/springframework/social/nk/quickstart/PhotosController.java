package org.springframework.social.nk.quickstart;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.inject.Inject;

import org.apache.shindig.protocol.RestfulCollection;
import org.apache.shindig.social.opensocial.model.Album;
import org.springframework.social.nk.api.AlbumOperations;
import org.springframework.social.nk.api.MediaItemOperations;
import org.springframework.social.nk.api.Nk;
import org.springframework.social.nk.api.impl.AlbumTemplate;
import org.springframework.social.nk.api.impl.MediaItemTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/photos")
public class PhotosController {

    private final Nk nk;

    @Inject
    public PhotosController(Nk nk) {
        this.nk = nk;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(@RequestParam(value = "groupId", required = false) String groupId, Model model) {

        AlbumOperations<AlbumTemplate> albumOper = this.nk.albumOperations();
        MediaItemOperations<MediaItemTemplate> mediaItemOper = this.nk.mediaItemOperations();

        RestfulCollection<Album> currentUserAlbums = albumOper.getCurrentUserAlbums();
        // RestfulCollection<Album> groupAlbums = albumOper.getGroupAlbums(groupId);

        model.addAttribute("currentUserAlbums", currentUserAlbums);
        // model.addAttribute("groupAlbums", groupAlbums);

        return "photos";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addPhoto(@RequestParam("albumId") String albumId, @RequestParam("description") String description,
            Model model) throws Exception {
        MediaItemOperations<MediaItemTemplate> mediaItemOper = this.nk.mediaItemOperations();
        
        InputStream in = new FileInputStream("c:\\tmp\\Diana2.jpg");
        InputStream in2 = new FileInputStream("c:\\tmp\\Justin.jpg");
        String mimeType = "image/jpg";
        mediaItemOper.addCurrentUserPhoto(albumId, in, mimeType, description);
        mediaItemOper.addCurrentUserPhoto(albumId, in2, mimeType, description);
        
        
        return "photos";
    }
}
