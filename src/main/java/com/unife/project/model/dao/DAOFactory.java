package com.unife.project.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.unife.project.util.DatabaseConnection;

public class DAOFactory {

    private static DAOFactory instance;
    private static Connection connection;

    static {
        // Inizializza la connessione al database
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public static AnimaleDAO getAnimaleDAO() {
        return new AnimaleDAOImpl(connection);
    }

    public static CisternaDAO getCisternaDAO() {
        return new CisternaDAOImpl(connection);
    }

    public static IrrigazioneDAO getIrrigazioneDAO() {
        return new IrrigazioneDAOImpl(connection);
    }

    public static IrrigazioneCisternaDAO getIrrigazioneCisternaDAO() {
        return new IrrigazioneCisternaDAOImpl(connection);
    }

    public static ListinoDAO getListinoDAO() {
        return new ListinoDAOImpl(connection);
    }

    public static MagazzinoDAO getMagazzinoDAO() {
        return new MagazzinoDAOImpl(connection);
    }

    public static PiantagioneDAO getPiantagioneDAO() {
        return new PiantagioneDAOImpl(connection);
    }

    public static ProdottoDAO getProdottoDAO() {
        return new ProdottoDAOImpl(connection);
    }

    public static RaccoltaDAO getRaccoltaDAO() {
        return new RaccoltaDAOImpl(connection);
    }

    public static StallaDAO getStallaDAO() {
        return new StallaDAOImpl(connection);
    }

    public static UtenteDAO getUtenteDAO() {
        return new UtenteDAOImpl(connection);
    }
    
    public static VisitaVeterinariaDAO getVisitaVeterinariaDAO() {
        return new VisitaVeterinariaDAOImpl(connection);
    }

    public static ZonaDAO getZonaDAO() {
        return new ZonaDAOImpl(connection);
    }
}