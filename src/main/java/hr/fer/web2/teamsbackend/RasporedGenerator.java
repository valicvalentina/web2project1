package hr.fer.web2.teamsbackend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import hr.fer.web2.teamsbackend.domain.Participant;
import hr.fer.web2.teamsbackend.domain.Runda;

@Component
public class RasporedGenerator {
	public static List<Runda> generirajRasporedKola(List<Participant> natjecatelji) {
List<Runda> rasporedKola = new ArrayList<>();


        int brojNatjecatelja = natjecatelji.size();
        
       if (brojNatjecatelja % 2 != 0) {
            // slobodan tim za neparan broj natjecatelja
    	   Participant participant = new Participant();
    	   participant.setName("Slobodan");
            natjecatelji.add(participant);
            brojNatjecatelja++;
        }
        
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < brojNatjecatelja; i++) {
            indices.add(i);
        }
        
        for (int kolo = 1; kolo < brojNatjecatelja; kolo++) {
            for (int i = 0; i < brojNatjecatelja / 2; i++) {
                int index1 = indices.get(i);
                int index2 = indices.get(brojNatjecatelja - 1 - i);
                
                Participant natjecatelj1 = natjecatelji.get(index1);
                Participant natjecatelj2 = natjecatelji.get(index2);
                
              if (!natjecatelj1.getName().equals("Slobodan") && !natjecatelj2.getName().equals("Slobodan")) {
                    Runda runda = new Runda(kolo, natjecatelj1, natjecatelj2);
                    rasporedKola.add(runda);
               }
            }
            
            // Rotiranje natjecatelja, osim prvog (slobodnog) ako postoji.
            if (natjecatelji.get(1).getName().equals("Slobodan")) {
                // Ako je drugi natjecatelj slobodan, preskočim ga pri rotaciji.
                Collections.rotate(indices.subList(2, brojNatjecatelja), 1);
           } else {
                Collections.rotate(indices.subList(1, brojNatjecatelja), 1);
            }
        }
        
        return rasporedKola;
    }
}
