package DAO;

import java.util.ArrayList;

public interface DAOInterface<Generic> {

	public int insert(Generic reneric);

	public int del(Generic reneric);

	public int update(Generic reneric);

	public Generic selectByID(Generic generic);

	public ArrayList<Generic> selectAll();
}
