pipeline{
  agent any
  stages{
    stage('Compile stage'){
      steps{
        echo 'compiling'
      }
    }
    stage('Testing'){
      steps{
        echo 'Testing'
      }
    }
    stage('Deploy') {
      steps{
        echo 'Deployment'
        script{
          echo 'Inside script'
          def TODAY = new Date()
          def folder = fileExists("${WORKSPACE}/tmp")
          if(!folder){
            folder.mkdirs()
          }
          def versionFile = project.file("${WORKSPACE}/tmp/version.txt");
          def appID = (${BUILD_ID}==null)? TODAY : ${BUILD_ID}
          def appTag = ${JOB_NAME}
          def appVer = (${BUILD_NUMBER}==null)?"LOCAL BUILD" : ${BUILD_NUMBER}
          versionFile.delete()
          versionFile << "id: ${appID}\ntag: ${appTag}\nversion:${appVer}\n"
          sh "ls -l ${WORKSPACE}/tmp/version.txt"
          sh "cat ${WORKSPACE}/tmp/version.txt"
        }
      }
    }
  }
}
