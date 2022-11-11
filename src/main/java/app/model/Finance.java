package app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tresor")
public class Finance implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "date",unique = true)
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="atelier",referencedColumnName="id",nullable=false)
    private Atelier atelier;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "tresor")
    private List<Recipe> recipes =new ArrayList<>();

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "tresor")
    private List<Spent> spents =new ArrayList<>();

    public void addRecette(Recipe recipe){
        recipe.setFinance(this);
        this.recipes.add(recipe);
    }

    public double getTotalRecipe(){
        double total = 0;
        for (Recipe recipe :getRecipes()) {
            total+= recipe.getMontant();
        }
        return total;
    }

    public void addDepense(Spent spent){
        spent.setFinance(this);
        this.spents.add(spent);
    }

    public double getTotalSpent(){
        double total=0;
        for (Spent spent :getSpents()) {
            total+= spent.getMontant();
        }
        return total;
    }
}
