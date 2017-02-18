# Journal for humans

This is a completely offline journal desktop application designed to interact with people in a natural way.

As a terminal, it takes in declarative commands from the user, and the journal uses Natural Language Processing to discover what the user is trying to do.

In addition, it uses Sentiment Analysis to assist the program with creating appropriate responses and predicting mood swings.

I plan on making the AI powering this program to not to simply grade an entry on a happiness scale of 0 to 1 (which is what every sentiment analysis library is doing, and, frankly, is a completely inaccurate of display human emotions), but to actually understand the various emotions that are at hand, their respective intensities, and the cause of those emotions.

## Supported actions:
Journal actions:
* Quit/exit the program
* Write today's journal
* Enter diary for a previous day I might've missed
* See previous entries

Emotion actions:
* Emotional support (explicitly, or implicitly after rant/journal entry)
* See mood swings over time
* Ask for mood predictions
* Tell if program is on the mark or wrong with its analysis of emotion
* 'Draw my life'

Program actions:
* Set the user's name
* Change font, size, color
* Change background

Automatic actions:
* Greet with the user's name (in varying ways depending on mood predictions)
* Tell user how they're feeling and why
* Ask user for confirmation on unknown actions
* Subtly painting background that reflects user's mood and experience over time

## (Probable) technology stack:
* (Confirmed) JavaFX for GUI front-end
* JUnit for testing
* Stanford's CoreNLP for language processing and intent extraction
* H2 for embedded database that stores various configs and entries
* Deeplearning4J for emotion predictions and learning from entries
* (Unknown) For painting?

To Joe: you think I'm crazy, huh?