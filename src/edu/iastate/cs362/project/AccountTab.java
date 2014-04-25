package edu.iastate.cs362.project;

import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class AccountTab extends javax.swing.JPanel {

	User loggedUser;
	Account loggedAccount;
	String id;
	FrontPage parent;
	
    /**
     * Creates new form AccountTab
     */
    public AccountTab(String id, FrontPage parent) {
    	this.id = id;
        initComponents();
        this.parent = parent;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        balance = new javax.swing.JLabel();
        buttonDeposit = new javax.swing.JButton();
        buttonWithdraw = new javax.swing.JButton();
        buttonClose = new javax.swing.JButton();
        buttonStatement = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Balance: ");

        setMaximumSize(new java.awt.Dimension(416, 286));
        setPreferredSize(new java.awt.Dimension(416, 286));

        balance.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        balance.setText("$0.00");

        buttonDeposit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonDeposit.setText("Deposit");

        buttonWithdraw.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonWithdraw.setText("Withdraw");

        buttonClose.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonClose.setText("Close Account");

        buttonStatement.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonStatement.setText("View Statement");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonWithdraw, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonStatement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonDeposit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(balance, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(163, 163, 163))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(balance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonWithdraw, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        
        loggedUser = Main.controller.getLoginUser();
    	loggedAccount = loggedUser.getAccount(id);
    	
    	updateBalance();
        
        buttonDeposit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	String amount = (String)JOptionPane.showInputDialog(
                        "Deposit amount:");
            	if(amount != null)
            	{
            		Main.controller.depositMoney(loggedUser.getUsername() + "-" + id, Double.valueOf(amount));
            		updateBalance();
            		parent.updateTotal();
            	}
            }
        });
        buttonWithdraw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	String amount = (String)JOptionPane.showInputDialog(
                        "Withdraw amount:");
            	if(amount != null)
            	{
            		Main.controller.withdrawMoney(loggedUser.getUsername() + "-" + id, Double.valueOf(amount));
            		updateBalance();
            		parent.updateTotal();
            	}
            }
        });
        buttonClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	///TO DO
            }
        });
        buttonStatement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	///TO DO
            }
        });
    }// </editor-fold>//GEN-END:initComponents
    
    public void updateBalance()
    {
    	balance.setText("$" + new DecimalFormat("#0.00").format(loggedAccount.getBalance()));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel balance;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonDeposit;
    private javax.swing.JButton buttonStatement;
    private javax.swing.JButton buttonWithdraw;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
