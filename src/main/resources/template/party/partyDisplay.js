const TBodyElement = document.getElementById("party-list")

fetch(baseURL+ "/parties")
    .then(response => response.json())
    .then(party => {
        party.map(createParty);
    });

function createParty (party) {
    const partyElement =document.createElement("div");
    partyElement.innerHTML = `
        
        <div class=wrapper>
        <img src=${party.partyLogo} alt="party-logo" width="100">
        <p class="party-name">${party.partyName}</p>
        <p class="party-members">medlemmer: ${party.partyMembers}</p>
        <p>Stemmer: ${party.partyVotePercent} %</p>
        </div>
        
        
       `;

    TBodyElement.appendChild(partyElement);
}
