package com.project.football.controller;

import com.project.football.item.IItem;
import com.project.football.item.TeamItem;
import com.project.football.model.Player;
import com.project.football.model.Team;
import com.project.football.repository.TeamRepository;
import com.project.football.services.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clubs")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private IUploadFileService uploadFileService;

    private IItem getItem(Team team){
        return new TeamItem(
                team.getIdTeam(),
                team.getName(),
                team.getTrainer(),
                team.getImage());
    }

    @GetMapping
    public List<IItem> getAll(){
        List<Team> teamList = teamRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!teamList.isEmpty()){
            for(Team team: teamList){
                itemList.add(getItem(team));
            }
        }

        return itemList;
    }

    @GetMapping("/{id}")
    public IItem findById(@PathVariable("id") long id){
        var playerMatch= teamRepository.findById(id).orElse(null);
        IItem item = new TeamItem();

        if(playerMatch!=null){
            item = getItem(playerMatch);
        }
        return item;
    }

    @PostMapping
    public IItem create(@RequestBody TeamItem teamMTP){
        var team = new Team(0, teamMTP.getName(), teamMTP.getTrainer(), teamMTP.getImage());
        team = teamRepository.save(team);

        return getItem(team);
    }

    @PutMapping
    public IItem update(@RequestBody TeamItem teamMTP){
        var team = teamRepository.findById(teamMTP.getIdTeam()).orElse(null);
        team.setName(teamMTP.getName());
        team.setTrainer(teamMTP.getTrainer());
        team.setImage(teamMTP.getImage());
        team = teamRepository.save(team);

        return getItem(team);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        Team team = teamRepository.findById(id).orElse(null);
        String previusNameImage = team.getImage();
        uploadFileService.delete(previusNameImage); // delete upload image
        teamRepository.deleteById(id);
    }


    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id){
        Map<String, Object> response = new HashMap<>();

        Team team = teamRepository.findById(id).orElse(null);
        if(!file.isEmpty()) {
            String fileName = null;
            try {
                fileName = uploadFileService.copy(file);
            } catch (IOException e) {
                response.put("message", "error uploading the image");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String previusNameImage = team.getImage(); // get image team
            uploadFileService.delete(previusNameImage); //delete previous image if it exists
            team.setImage(fileName);
            teamRepository.save(team);
            response.put("player", team);
            response.put("message", "You have successfully uploaded the image: " + fileName);

        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/upload/{nameImage:.+}")
    public ResponseEntity<Resource> viewImage(@PathVariable String nameImage){
        Resource resource = null;
        try {
            resource = uploadFileService.load(nameImage);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
