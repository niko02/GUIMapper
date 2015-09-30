/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiMapper.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Captura {

    private StringProperty Objeto;
    private StringProperty Accion;
    private StringProperty Imagen;
    private StringProperty ImagenId;
    private IntegerProperty x;
    private IntegerProperty y;

    /**
     * Default constructor.
     */
    public Captura() {
        this(null,null,null,null,0,0);
    }

    public Captura(String objeto, String accion, String imagen,String imagenId, int x, int y) {
        this.Objeto = new SimpleStringProperty(objeto);
        this.Accion = new SimpleStringProperty(accion);
        this.Imagen = new SimpleStringProperty(imagen);
        this.ImagenId = new SimpleStringProperty(imagenId);
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    public StringProperty objetoProperty() {
        return Objeto;
    }

    public StringProperty accionProperty() {
        return Accion;
    }

    public StringProperty imagenProperty() {
        return Imagen;
    }
    
    public StringProperty imagenIdProperty() {
        return ImagenId;
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public IntegerProperty yProperty() {
        return y;
    }

    /**
     * @return the Objeto
     */
    public String getObjeto() {
        return Objeto.get();
    }

    /**
     * @param Objeto the Objeto to set
     */
    public void setObjeto(String Objeto) {
        this.Objeto.set(Objeto);
    }

    /**
     * @return the Accion
     */
    public String getAccion() {
        return Accion.get();
    }

    /**
     * @param Accion the Accion to set
     */
    public void setAccion(String Accion) {
        this.Accion.set(Accion);
    }

    /**
     * @return the Imagen
     */
    public String getImagen() {
        return Imagen.get();
    }

    /**
     * @param Imagen the Imagen to set
     */
    public void setImagen(String Imagen) {
        this.Imagen.set(Imagen);
    }
    
    /**
     * @return the ImagenId
     */
    public String getImagenId() {
        return ImagenId.get();
    }

    /**
     * @param ImagenId the Imagen to set
     */
    public void setImagenId(String ImagenId) {
        this.ImagenId.set(ImagenId);
    }

    /**
     * @return the x
     */
    public int getX() {
        return x.get();
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x.set(x);
    }

    /**
     * @return the y
     */
    public int getY() {
        return y.get();
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y.set(y);
    }

}
