/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personswingappswingworker;

import com.asgteach.familytree.model.FamilyTreeManager;
import com.asgteach.familytree.model.Person;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author GiorgioP
 */
public class PersonJFrame extends javax.swing.JFrame {

    private final DefaultMutableTreeNode top = new DefaultMutableTreeNode("People");
    private final FamilyTreeManager ftm = FamilyTreeManager.getInstance();
    private Person thePerson;
    private static final Logger logger = Logger.getLogger(PersonJFrame.class.getName());
    private boolean changeOk = false;

    /**
     * Creates new form PersonJFrame
     */
    private PersonJFrame() {
        logger.setLevel(Level.FINE);
        /* define handler to display messages on the console */
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.FINE);
        logger.addHandler(handler);
        try {
            /* define a second handler to write messages to a file */
            Handler fileHandler = new FileHandler();
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);
            logger.log(Level.FINE, "Created File Handler {0}", fileHandler.);
        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, "Couldn't create FileHandler", ex);
        }

        buildData();
        initComponents();
        personTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        createNodes(top);
    }

    private void configureListeners() {
        /* listeners are registered after constructor returns for thread safety */
        ftm.addPropertyChangeListener(familyTreeListener);
        personTree.addTreeSelectionListener(treeSelectionListener);
        updateButton.addActionListener(updateListener);
        firstTextField.getDocument().addDocumentListener(docListener);
        middleTextField.getDocument().addDocumentListener(docListener);
        lastTextField.getDocument().addDocumentListener(docListener);
        suffixTextField.getDocument().addDocumentListener(docListener);
        notesTextArea.getDocument().addDocumentListener(docListener);
        maleButton.addActionListener(radioButtonListener);
        femaleButton.addActionListener(radioButtonListener);
        unknowButton.addActionListener(radioButtonListener);
        processAllButton.addActionListener(processAllListener);
    }

    public static PersonJFrame newInstance() {
        PersonJFrame pjf = new PersonJFrame();
        pjf.configureListeners();
        return pjf;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderButtonGroup = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        processAllButton = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        jScrollPane3 = new javax.swing.JScrollPane();
        statusTextArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        notesTextArea = new javax.swing.JTextArea();
        maleButton = new javax.swing.JRadioButton();
        suffixTextField = new javax.swing.JTextField();
        femaleButton = new javax.swing.JRadioButton();
        unknowButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        firstTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        middleTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lastTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        updateButton = new javax.swing.JButton();
        updateButton.setEnabled(false);
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        personTree = new javax.swing.JTree(top);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        processAllButton.setText("Process All");
        processAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processAllButtonActionPerformed(evt);
            }
        });

        statusTextArea.setColumns(20);
        statusTextArea.setRows(5);
        jScrollPane3.setViewportView(statusTextArea);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(processAllButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(processAllButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3))
        );

        notesTextArea.setColumns(20);
        notesTextArea.setRows(5);
        notesTextArea.setName(""); // NOI18N
        jScrollPane2.setViewportView(notesTextArea);

        genderButtonGroup.add(maleButton);
        maleButton.setText("Male");
        maleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleButtonActionPerformed(evt);
            }
        });

        suffixTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suffixTextFieldActionPerformed(evt);
            }
        });

        genderButtonGroup.add(femaleButton);
        femaleButton.setText("Female");

        genderButtonGroup.add(unknowButton);
        unknowButton.setText("Unknow");

        jLabel1.setText("First:");

        firstTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstTextFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Middle:");

        jLabel3.setText("Last:");

        jLabel4.setText("Suffix:");

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Notes:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(maleButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(femaleButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(unknowButton)
                                .addGap(0, 59, Short.MAX_VALUE))
                            .addComponent(middleTextField)
                            .addComponent(suffixTextField)
                            .addComponent(lastTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(firstTextField, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(updateButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(firstTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(middleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lastTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(suffixTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(maleButton)
                            .addComponent(femaleButton)
                            .addComponent(unknowButton)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateButton)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(personTree);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void maleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maleButtonActionPerformed

    private void firstTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstTextFieldActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateButtonActionPerformed

    private void suffixTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suffixTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_suffixTextFieldActionPerformed

    private void processAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processAllButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_processAllButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            logger.log(Level.SEVERE, "Coulnd't apply Nimbus look and feel", ex);
//        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PersonJFrame.newInstance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton femaleButton;
    private javax.swing.JTextField firstTextField;
    private javax.swing.ButtonGroup genderButtonGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField lastTextField;
    private javax.swing.JRadioButton maleButton;
    private javax.swing.JTextField middleTextField;
    private javax.swing.JTextArea notesTextArea;
    private javax.swing.JTree personTree;
    private javax.swing.JButton processAllButton;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTextArea statusTextArea;
    private javax.swing.JTextField suffixTextField;
    private javax.swing.JRadioButton unknowButton;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables

    private void buildData() {
        ftm.addPerson(new Person("Homer", "Simpson", Person.Gender.MALE));
        ftm.addPerson(new Person("Marge", "Simpson", Person.Gender.FEMALE));
        ftm.addPerson(new Person("Bart", "Simpson", Person.Gender.MALE));
        ftm.addPerson(new Person("Lisa", "Simpson", Person.Gender.FEMALE));
        ftm.addPerson(new Person("Maggie", "Simpson", Person.Gender.FEMALE));
        logger.log(Level.FINE, ftm.getAllPeople().toString());
    }

    private void createNodes(DefaultMutableTreeNode top) {
        /* build JTree model using FamilyTreeManager */
        ftm.getAllPeople().forEach(p -> top.add(new DefaultMutableTreeNode(p)));
    }

    private void updateModel() {
        if (!changeOk) {
            return;
        }
        updateButton.setEnabled(false);
        thePerson.setFirstname(firstTextField.getText());
        thePerson.setMiddlename(middleTextField.getText());
        thePerson.setLastname(lastTextField.getText());
        thePerson.setSuffix(suffixTextField.getText());
        if (maleButton.isSelected()) {
            thePerson.setGender(Person.Gender.MALE);
        } else if (femaleButton.isSelected()) {
            thePerson.setGender(Person.Gender.FEMALE);
        } else if (unknowButton.isSelected()) {
            thePerson.setGender(Person.Gender.UNKNOW);
        }
        thePerson.setNotes(notesTextArea.getText());
    }

    private final ActionListener processAllListener = (ActionEvent evt) -> {
        /* get list of people from ftm */
        final Collection<Person> processList = ftm.getAllPeople();
        processAllButton.setEnabled(false);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        logger.log(Level.FINE, "Process all requested for {0}", processList);
        SwingWorker<Collection<Person>, Person> worker = new SwingWorker<Collection<Person>, Person>() {
            final int count = processList.size();

            @Override
            protected Collection<Person> doInBackground() throws Exception {
                int i = 0;
                for (Person person : processList) {
                    try {
                        /* do something with each person */
                        doProcess(person);
                        logger.log(Level.FINE, "Processing person {0}...", person);
                        /* make available to process() */
                        publish(person);
                        setProgress(100 * (++i) / count);
                        /* simulate a long-runnign task */
                        Thread.sleep(500);
                    } catch (Exception e) {
                        logger.log(Level.WARNING, null, e);
                    }
                }
                return processList;
            }

            private void doProcess(Person p) {
                p.setFirstname(p.getFirstname().toUpperCase());
                p.setMiddlename(p.getMiddlename().toUpperCase());
                p.setLastname(p.getLastname().toUpperCase());
                p.setSuffix(p.getSuffix().toUpperCase());
            }

            @Override
            protected void done() {
                try {
                    if (!isCancelled()) {
                        logger.log(Level.FINE, "Done! Processing all {0}", get());
                    }
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(PersonJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                /* reset any GUI elements */
                progressBar.setValue(0);
                progressBar.setStringPainted(false);
                statusTextArea.setText("");
                processAllButton.setEnabled(true);
            }

            @Override
            protected void process(List<Person> chunks) {
                chunks.stream().forEach((p) -> {
                    statusTextArea.append(p + "\n");
                });
            }

        };
        worker.addPropertyChangeListener((PropertyChangeEvent pce) -> {
            if ("progress".equals(pce.getPropertyName())) {
                progressBar.setValue((int) pce.getNewValue());
            }
        });
        worker.execute();
    };

    /* define listener */
    private final ActionListener updateListener = (ActionEvent evt) -> {
        /* first update the model from the UI */
        updateModel();
        /* copy Person for background thread */
        final Person person = new Person(thePerson);
        SwingWorker<Person, Void> worker = new SwingWorker<Person, Void>() {

            @Override
            protected Person doInBackground() throws Exception {
                /* simulate a long running process*/
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    logger.log(Level.WARNING, null, e);
                }
                /* save in background thread */
                logger.log(Level.FINE, "calling ftm for person {0}", person);
                ftm.updatePerson(person);
                /* only if interested in accessing person after background thread finishes otherwise return null */
                return person;
            }

            @Override
            protected void done() {
                /* invoked after background thread finishes */
                try {
                    if (!isCancelled()) {
                        logger.log(Level.FINE, "Done! Saving person {0}", get());
                    }
                } catch (Exception e) {
                    logger.log(Level.SEVERE, null, e);
                }
            }
        };
        /* invoke background thread */
        worker.execute();
    };

    /* document listener for text fields and text area */
    private DocumentListener docListener = new DocumentListener() {

        @Override
        public void insertUpdate(DocumentEvent e) {
            if (changeOk) {
                modify();
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            if (changeOk) {
                modify();
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (changeOk) {
                modify();
            }
        }
    };

    /* action listener for radio buttons */
    private final ActionListener radioButtonListener = (ActionEvent evt) -> {
        if (changeOk) {
            modify();
        }
    };

    private final PropertyChangeListener familyTreeListener = (PropertyChangeEvent evt) -> {
        if (evt.getNewValue() != null && evt.getPropertyName().equals(FamilyTreeManager.PROP_PERSON_UPDATED)) {
            Person person = (Person) evt.getNewValue();
            DefaultTreeModel model = (DefaultTreeModel) personTree.getModel();
            for (int i = 0; i < model.getChildCount(top); i++) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) model.getChild(top, i);
                if (person.equals(node.getUserObject())) {
                    node.setUserObject(person);
                    /* let the model know we made a change */
                    model.nodeChanged(node);
                    logger.log(Level.FINE, "Node updated: {0}", node);
                    break;
                }

            }
        }
    };
    private final TreeSelectionListener treeSelectionListener = (TreeSelectionEvent evt) -> {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) personTree.getLastSelectedPathComponent();
        if (null == node) {
            updateButton.setEnabled(false);
            return;
        }
        if (node.isLeaf()) {
            Person person = (Person) node.getUserObject();
            logger.log(Level.FINE, "{0} selected", person);
            editPerson(person);
        } else {
            clearForm();
            updateButton.setEnabled(false);
        }
    };

    private void editPerson(Person person) {
        thePerson = person;
        updateForm();
    }

    private void updateForm() {
        changeOk = false;
        updateButton.setEnabled(false);
        firstTextField.setText(thePerson.getFirstname());
        middleTextField.setText(thePerson.getMiddlename());
        lastTextField.setText(thePerson.getLastname());
        suffixTextField.setText(thePerson.getSuffix());
        if (thePerson.getGender() == Person.Gender.MALE) {
            maleButton.setSelected(true);
        } else if (thePerson.getGender() == Person.Gender.FEMALE) {
            femaleButton.setSelected(true);
        } else if (thePerson.getGender() == Person.Gender.UNKNOW) {
            unknowButton.setSelected(true);
        }
        notesTextArea.setText(thePerson.getNotes());
//        updateButton.setEnabled(true);
        changeOk = true;

    }

    private void clearForm() {
        updateButton.setEnabled(false);
        changeOk = false;
        firstTextField.setText("");
        middleTextField.setText("");
        lastTextField.setText("");
        suffixTextField.setText("");
        maleButton.setSelected(false);
        femaleButton.setSelected(false);
        unknowButton.setSelected(false);
        genderButtonGroup.clearSelection();
        notesTextArea.setText("");
    }

    private void modify() {
        updateButton.setEnabled(true);
    }
}
