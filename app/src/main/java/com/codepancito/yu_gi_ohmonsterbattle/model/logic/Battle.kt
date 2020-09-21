package com.codepancito.yu_gi_ohmonsterbattle.model.logic

import androidx.lifecycle.MutableLiveData
import com.codepancito.yu_gi_ohmonsterbattle.model.db.MonsterCardEntity

class Battle(var playerDeck: List<MonsterCardEntity>, var opponentDeck: List<MonsterCardEntity>) {

    var playerPoints = MutableLiveData(0)
    var opponentPoints = MutableLiveData(0)
    private var damageToPlayer = 0
    private var damageToOpponent = 0
    var deckPosition = MutableLiveData(-1)
    private var currentTurn = 0
    var messageList = MutableLiveData(mutableListOf<String>("Presiona el botón para empezar la Batalla"))

    fun startTurn() {
        if(currentTurn < 5) {

            damageToPlayer = 0
            damageToOpponent = 0

            deckPosition.value = deckPosition.value!! + 1

            val playerCard = playerDeck[currentTurn]
            val opponentCard = opponentDeck[currentTurn]

            addMessage("Turno número ${++currentTurn}")

            addMessage("Tú juegas a ${playerCard.name}")
            addMessage("Tu oponente juega a ${opponentCard.name}")

            var damage = playerCard.attack - opponentCard.defense

            if(damage >= 0)
                damageToOpponent += damage
            else
                damageToPlayer += (damage * -1)

            addMessage("Tú atacas!!... ${
                when {
                    damage == 0 -> "pero no causas daño"
                    damage > 0 -> "y tu oponente recibe $damage de daño"
                    else -> "pero recibes ${damage * -1} de daño"
                }
            }")

            damage = opponentCard.attack - playerCard.defense

            if(damage >= 0)
                damageToPlayer += damage
            else
                damageToOpponent += (damage * -1)

            addMessage("Tu oponente ataca!!... ${
                when {
                    damage == 0 -> "pero no causas daño"
                    damage > 0 -> "y Tú recibes $damage de daño"
                    else -> "pero recibe ${damage * -1} de daño"
                }
            }")

            when {
                damageToPlayer < damageToOpponent -> {
                    playerPoints.value = playerPoints.value!! + 1
                    addMessage("Tú has causado el mayor daño y ganas un punto")
                }
                damageToPlayer > damageToOpponent -> {
                    opponentPoints.value = opponentPoints.value!! + 1
                    addMessage("Tu oponente ha causado el mayor daño y gana un punto")
                }
                else -> {
                    addMessage("Ambos han causado el mismo daño. Este turno termina en empate")
                }
            }

            if(playerPoints.value!! == 3 || opponentPoints.value!! == 3)
                currentTurn = 5

            if(currentTurn == 5) {
                addMessage("La Batalla ha terminado!!!. ${
                when {
                    playerPoints.value!! > opponentPoints.value!! -> {
                        "Tú ganas por ${playerPoints.value!!} contra ${opponentPoints.value!!}"
                    }
                    opponentPoints.value!! > playerPoints.value!! -> {
                        "Tu oponente gana por ${opponentPoints.value!!} contra ${playerPoints.value!!}"
                    }
                    else -> {
                        "Es un empate ${playerPoints.value!!} contra ${opponentPoints.value!!}"
                    }
                }
                }")
            }
        }
    }

    private fun addMessage(message: String) {
        messageList.value!!.add(0, message)
        messageList.value = messageList.value
    }



}