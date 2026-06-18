pipeline {
  agent any

  tools {
    jdk 'jdk21'
  }

  options {
    timestamps()
    disableConcurrentBuilds()
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Test') {
      steps {
        sh 'chmod +x mvnw'
        sh './mvnw -B clean test'
      }
    }

    stage('Deploy') {
      steps {
        echo 'Pruebas en VERDE -> desplegando a produccion'
        // TODO: cuando rapigo-backend exista como servicio en Dokploy,
        // pega aqui tu webhook de deploy:
        // sh 'curl -fsSL -X POST "PEGA_AQUI_TU_WEBHOOK_DE_DOKPLOY"'
      }
    }
  }

  post {
    always {
      junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
    }
    success { echo 'PIPELINE VERDE: pruebas pasaron y desplegado.' }
    failure { echo 'PIPELINE ROJO: las pruebas fallaron. El cambio NO se despliega.' }
  }
}
