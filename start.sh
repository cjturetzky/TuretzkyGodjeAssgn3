/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

brew install mvn

mvn package 

java -jar target/assignment3-1.0-SNAPSHOT.jar