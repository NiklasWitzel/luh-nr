package view;

import java.awt.Color;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ParsePDF;
/**
 * $Id$
 * $LastChangedDate$
 * 
 * @author Fadi M. H. Asbih
 * @email fadi_asbih@yahoo.de
 * @version $Revision$
 * @copyright $Date$
 * 
 * TERMS AND CONDITIONS:
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
public class Browse {
	
    private String filename;
    private String dir;
    private String path;
	
	public Browse(View view, ParsePDF pdf) {

		JFileChooser c = new JFileChooser();
        c.setMultiSelectionEnabled(false);
        c.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF-Notenspiegel", "pdf");
        c.setFileFilter(filter);
        // Demonstrate "Open" dialog:
        int rVal = c.showOpenDialog(view);
        if (rVal == JFileChooser.APPROVE_OPTION) {
        	view.progressBar.setIndeterminate(true);
            filename = c.getSelectedFile().getName();
            dir = c.getCurrentDirectory().toString();
            setPath(dir + "/" + filename);
            try {
                    pdf.parseFile(getPath());
//                  pdf = new ReadExcel(getPath());
                    view.getStatus().setText(
							 " "+ pdf.getSubject()+
							 "<br> " + pdf.getCertificate()+
	      				     "<br> Anzahl benotete F�cher: "+pdf.getNumberOfSubjectsWithGrade()+" ["+(int)pdf.getWeightedCredits()+" CP]"+
	      					 "<br> Anzahl unbenotete F�cher: "+pdf.getNumberOfSubjectsWithoutGrade()+" ["+(int)pdf.getUnweightedCredits()+" CP]"+
	      					 "<br> Anzahl gesamte F�cher: "+pdf.getNumberOfSubjects()+
     						 "<br> Credit Points: "+"<b>"+(int)pdf.getCredits()+"</b>"+
     						 "<br> Note: "+"<b>"+pdf.getFinalGrade()+"</b>"+
     						 "<br> Abschlussarbeit starten: "+pdf.getStartThesis()+
     						 "<br> Studium Geschafft in Prozent... ");
//                  this.getStatus().
                    view.getStatus().setForeground(Color.black.darker());
                    view.progressBar.setIndeterminate(false);
                    view.progressBar.setValue((int)pdf.getPercent());
                    view.progressBar.setStringPainted(true);
                    
//                  generate.setEnabled(true);
            } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                    view.getStatus().setText("ERROR");
                    view.getStatus().setForeground(Color.red.darker());
            }
            // System.out.println(dir+"/"+filename);
            
        }
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
