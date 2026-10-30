package es.us.dp1.chess.tournament.match;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.us.dp1.chess.tournament.exceptions.UnfeasibleMatchException;
import es.us.dp1.chess.tournament.user.User;

@Service
public class ChessMatchService {

    MatchRepository repo;    

    @Autowired
    public ChessMatchService(MatchRepository repo) {
        this.repo = repo;        
    }

    @Transactional(readOnly = true)
    public Optional<ChessMatch> getMatchById(Integer id) {
        return repo.findById(id);
    }

    @Transactional(rollbackFor = UnfeasibleMatchException.class)
    public ChessMatch save(ChessMatch m) {
        // TODO: This method should check if the winner is null (draw or not finished) or if the winner is one of the players (whitePlayer or blackPlayer). If not, it should throw an exception (UnfeasibleMatchException). The startDate should be before the endDate in case it is not null.
        if (null == m.getFinish() || m.getFinish().isAfter(m.getStart())){
            if (null == m.getWinner() || m.getWinner().equals(m.getWhitePlayer()) || m.getWinner().equals(m.getBlackPlayer())) {
                return repo.save(m);
            } else {
                throw new UnfeasibleMatchException("The winner must be one of the players of the match.");
            }
        } else {
            throw new UnfeasibleMatchException("The start date must be before the finish date.");
        }
    }
    

    @Transactional(readOnly = true)
    public List<ChessMatch> getMatches() {
        return repo.findAll();
    }    
    

    

}
