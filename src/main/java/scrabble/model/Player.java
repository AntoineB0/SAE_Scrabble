package scrabble.model;

public class Player {
	public String name;
	public Integer score;
	public Rack rack;
	
	public Player(String name, Integer score, Rack rack) {
		this.name = name;
		this.score = score;
		this.rack = rack;
	}
	
	public Player(String name, Rack rack) {
		this.name = name;
		this.score = 0;
		this.rack = rack;
	}

	public String getName() {
		return name;
	}

	public Integer getScore() {
		return score;
	}

	public Rack getRack() {
		return rack;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public void setRack(Rack rack) {
		this.rack = rack;
	}

	@Override
	public String toString() {
		return name + " your score is "+ score+" !";
	}
}
