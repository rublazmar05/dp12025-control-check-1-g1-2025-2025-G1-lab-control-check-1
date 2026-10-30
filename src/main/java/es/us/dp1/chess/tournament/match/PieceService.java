package es.us.dp1.chess.tournament.match;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class PieceService {

    PieceRepository repository;

    @Autowired
    public PieceService(Optional<PieceRepository> repository) {
        this.repository = repository.isPresent()?repository.get():null;
    }

    @Transactional(readOnly = true)
    public List<Piece> getPiecesByColorAndType(PieceColor color, String typeName) {
        // TODO: implement this method to solve Exercise 2a
        return repository.findByColorAndTypeName(color, typeName);
    }

    @Transactional(readOnly = true)
    public List<Piece> getAll() {
        // TODO: implement this method to solve Exercise 2a
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Piece getById(Integer i) {
        // TODO: implement this method to solve Exercise 2a
        return repository.findById(i).orElse(null);
    }

    @Transactional
    public Piece save(Piece piece) {
        // TODO: implement this method to solve Exercise 2a
        return repository.save(piece);
    }
    
}
