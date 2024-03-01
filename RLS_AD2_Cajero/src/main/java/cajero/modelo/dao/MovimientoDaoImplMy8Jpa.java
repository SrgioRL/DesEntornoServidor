package cajero.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cajero.modelo.entity.Movimiento;
import cajero.repository.MovimientoRepository;

@Repository
public class MovimientoDaoImplMy8Jpa implements MovimientoDao {

    @Autowired
    private MovimientoRepository mRepo;

    @Override
    public List<Movimiento> findAll() {
        return mRepo.findAll();
    }

    @Override
    public Movimiento findById(int idMovimiento) {
        return mRepo.findById(idMovimiento).orElse(null);
    }

    @Override
    public boolean createOne(Movimiento movimiento) {
        mRepo.save(movimiento);
        return true;
    }
}

