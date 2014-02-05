package com.ita.edu.softserve.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sources")
public class Source extends BaseEntity {

   

    @Column(name = "dirname", nullable = false)
    private String dirname = "d://lv-105_JAVA//sources//";

    /**
	 * @return the dirname
	 */
	public String getDirname() {
		return dirname;
	}




	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "source_id", nullable = false)
    private int sourceId;

   

    public Source() {
    	this.dirname = "d://lv-098_JAVA//sources//";
    }

   
    
   
    public void setDirname(String dirname) {
        this.dirname = dirname;
    }

}
