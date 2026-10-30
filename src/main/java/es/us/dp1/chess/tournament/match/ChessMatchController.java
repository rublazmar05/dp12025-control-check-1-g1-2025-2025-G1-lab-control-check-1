package es.us.dp1.chess.tournament.match;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.us.dp1.chess.tournament.user.UserService;
import jakarta.annotation.Generated;


@RestController 
@RequestMapping ("/api/v1/matches")
@PreAuthorize ("hasAuthority('PLAYER'")
public class ChessMatchController {

    UserService userService;
    ChessMatchService matchService;

    @Autowired
    public ChessMatchController(ChessMatchService service,UserService userService){
        this.matchService=service;
        this.userService=userService;    
    }

    /**
     * Get all matches
     * @return List of all matches
     */
    @GetMapping 
    public ResponseEntity<List<ChessMatch>> getAllMatches(){
        List<ChessMatch> matches = matchService.getMatches();
        return ResponseEntity.ok(matches);
    }

    /**
     * GET /api/v1/matches/{id}
     * Returns a single match by id, or 404 if it does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ChessMatch> getMatchById(@PathVariable("id") Integer id) {
        return matchService.getMatchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}