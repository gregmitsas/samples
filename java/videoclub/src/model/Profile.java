package model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.jdo.annotations.Unique;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Profile implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Unique
	@Basic(optional = false)
	private String label;

	@ManyToMany
	private ArrayList<Right> rights;

	// Empty constructor
	public Profile()
	{
	}

	// Constructor
	public Profile(String label)
	{
		this.label = label;
		rights = new ArrayList<Right>();
	}

	public long getId()
	{
		return id;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public void grantRight(Right r)
	{
		rights.add(r);
	}

	public ArrayList<String> getRightLabels()
	{
		ArrayList<String> rightLabels = new ArrayList<String>();
		for (Right r : this.rights)
			rightLabels.add(r.getLabel());
		return rightLabels;
	}

}
