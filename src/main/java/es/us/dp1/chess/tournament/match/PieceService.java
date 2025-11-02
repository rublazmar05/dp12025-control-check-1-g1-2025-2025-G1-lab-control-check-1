package es.us.dp1.chess.tournament.match;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class PieceService {

    PieceRepository repository;

    @Autowired(required = false)
    public PieceService(PieceRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Piece> getPiecesByColorAndType(PieceColor color, String typeName) {
        return this.repository.findByColorAndTypeName(color, typeName);
    }   

    @Transactional(readOnly = true)
    public List<Piece> getAll() {
        return this.repository.findAll();
    }

    @Transactional(readOnly = true)
    public Piece getById(Integer i) {
        return this.repository.findById(i).orElse(null);
    }

    @Transactional
    public Piece save(Piece piece) {
        return this.repository.save(piece);
    }
    
}
