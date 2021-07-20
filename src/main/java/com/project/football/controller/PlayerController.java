package com.project.football.controller;

import com.project.football.item.ArbiterItem;
import com.project.football.item.IItem;
import com.project.football.item.PlayerItem;
import com.project.football.model.Player;
import com.project.football.repository.*;
import com.project.football.request.PlayerRequest;
import com.project.football.services.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private IUploadFileService uploadFileService;

    private IItem getItem(Player player){
        return new PlayerItem(
                player.getIdPlayer(),
                player.getName(),
                player.getLastName(),
                player.getAge(),
                player.getTeam().getName(),
                player.getPosition().getName(),
                player.getImage(),
                player.getTeam().getIdTeam(),
                player.getPosition().getIdPosition());
    }

    @GetMapping
    public List<IItem> getAll(){
        List<Player> playerList = playerRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!playerList.isEmpty()){
            for(Player player: playerList){
                itemList.add(getItem(player));
            }
        }

        return itemList;
    }

    @GetMapping("/{id}")
    public IItem findById(@PathVariable("id") long id){
        var player= playerRepository.findById(id).orElse(null);
        IItem item = new ArbiterItem();

        if(player!=null){
            item = getItem(player);
        }
        return item;
    }

    @PostMapping
    public IItem create(@RequestBody PlayerRequest playerTMP){
        var position = positionRepository.findById(playerTMP.getIdPosition()).orElse(null);
        var team = teamRepository.findById(playerTMP.getIdTeam()).orElse(null);
        var player = new Player(0, playerTMP.getFirstName(), playerTMP.getLastName(), playerTMP.getAge(), team, position, playerTMP.getImage());
        player = playerRepository.save(player);

        return getItem(player);
    }

    @PutMapping
    public IItem update(@RequestBody PlayerRequest playerTMP){
        var player = playerRepository.findById(playerTMP.getIdPlayer()).orElse(null);
        var position = positionRepository.findById(playerTMP.getIdPosition()).orElse(null);
        var team = teamRepository.findById(playerTMP.getIdTeam()).orElse(null);
        player.setName(playerTMP.getFirstName());
        player.setLastName(playerTMP.getLastName());
        player.setAge(playerTMP.getAge());
        player.setTeam(team);
        player.setPosition(position);
        player.setImage(playerTMP.getImage());
        player = playerRepository.save(player);

        return getItem(player);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        Player player = playerRepository.findById(id).orElse(null);
        String previusNameImage = player.getImage();
        uploadFileService.delete(previusNameImage); // delete upload image

        playerRepository.deleteById(id);
    }

    @GetMapping("/team/{id}")
    public List<IItem> getAllByTeam(@PathVariable("id") long id){
        List<Player> playerList = playerRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!playerList.isEmpty()){
            for(Player player: playerList){
                if(player.getTeam().getIdTeam()==id){
                    itemList.add(getItem(player));
                }
            }
        }

        return itemList;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id){
        Map<String, Object> response = new HashMap<>();

        Player player = playerRepository.findById(id).orElse(null);
        if(!file.isEmpty()) {
            String fileName = null;
            try {
                fileName = uploadFileService.copy(file);
            } catch (IOException e) {
                response.put("message", "error uploading the image");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String previusNameImage = player.getImage(); // get image player
            uploadFileService.delete(previusNameImage); //delete previous image if it exists
            player.setImage(fileName);
            playerRepository.save(player);
            response.put("player", player);
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
