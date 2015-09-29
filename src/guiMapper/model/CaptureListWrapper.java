/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiMapper.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 * 
 * @author Marco Jakob
 */
@XmlRootElement(name = "captures")
public class CaptureListWrapper {

    private List<Captura> captures;

    @XmlElement(name = "capture")
    public List<Captura> getCaptures() {
        return captures;
    }

    public void setCaptures(List<Captura> captura) {
        this.captures = captura;
    }
}
