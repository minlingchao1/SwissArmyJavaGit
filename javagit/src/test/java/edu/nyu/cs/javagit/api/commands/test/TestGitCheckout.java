package edu.nyu.cs.javagit.api.commands.test;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.javagit.api.JavaGitException;
import edu.nyu.cs.javagit.api.commands.GitAdd;
import edu.nyu.cs.javagit.api.commands.GitCheckout;
import edu.nyu.cs.javagit.api.commands.GitCheckoutOptions;
import edu.nyu.cs.javagit.api.commands.GitCheckoutResponse;
import edu.nyu.cs.javagit.api.commands.GitCommit;
import edu.nyu.cs.javagit.test.utilities.FileUtilities;
import edu.nyu.cs.javagit.test.utilities.HelperGitCommands;


public class TestGitCheckout extends TestCase {

  private File repositoryDirectory;
  private String repositoryPath;
  private GitCommit gitCommit;
  private GitAdd gitAdd;
  private GitCheckout gitCheckout;
  private File file1;
  private File file2;
  
  @Before
  public void setUp() throws Exception {
    repositoryDirectory = FileUtilities.createTempDirectory("GitCheckoutTestRepository");
    HelperGitCommands.initRepo(repositoryDirectory);
    gitCommit = new GitCommit();
    gitAdd = new GitAdd();
    gitCheckout = new GitCheckout();
    file1 = FileUtilities.createFile(repositoryDirectory, "foobar01", 
    "Sameple Contents");
    file2 = FileUtilities.createFile(repositoryDirectory, "foobar02", 
    "Sameple Contents");   
    repositoryPath = repositoryDirectory.getAbsolutePath();
    List<String> filesToAdd = new ArrayList<String>();
    filesToAdd.add("foobar01");
    filesToAdd.add("foobar02");
    List<String> addOptions = new ArrayList<String>();
    gitAdd.add(repositoryPath, addOptions, filesToAdd);
    gitCommit.commit(repositoryDirectory, "New Repository");
  }
  
  /**
   * Test for creating a new branch switching to it from base master branch
   * @throws IOException
   * @throws JavaGitException
   */
  @Test
  public void testCreatingNewBranchFromMaster() 
    throws IOException, JavaGitException {
    GitCheckoutOptions options = new GitCheckoutOptions();
    options.setOptB("testBranch");
    String branch = "master";
    GitCheckoutResponse response = gitCheckout.checkout(repositoryDirectory, options, branch);
    assertEquals("New Branch created should be created with name- testBranch",
        "\"testBranch\"", response.getNewBranch());
  }
  
  /**
   * Test for checking out a locally deleted file from the repository.
   * @throws JavaGitException
   * @throws IOException
   */
  @Test
  public void testCheckingOutLocalllyDeletedFiles() throws JavaGitException, IOException{
    List<File> filePaths = new ArrayList<File>();
    filePaths.add(new File(repositoryPath+File.separator+"foobar01"));
    if ( file1.delete() ) { // locally delete the file
      // check out the file from the repository after deletion
      GitCheckoutResponse response = gitCheckout.checkout(repositoryDirectory, filePaths);
      if ( response.getError() != null ) {
        fail("Error: " + response.getError());
      }
      File checkedOutFile = new File(repositoryPath+File.separator+"foobar01");
      assertTrue(checkedOutFile.exists());
      FileUtilities.modifyFileContents(file2, "Test for append to a file");
      GitCheckoutOptions options = new GitCheckoutOptions();
      response = gitCheckout.checkout(repositoryDirectory, options, "master");
      assertEquals("Modified File exists", 1, response.getNoOfModifiedFiles());
    } else {
      fail("File delete failed");
    }
  }

  @After
  public void tearDown() throws Exception {
    FileUtilities.removeDirectoryRecursivelyAndForcefully(repositoryDirectory);
  }

}
