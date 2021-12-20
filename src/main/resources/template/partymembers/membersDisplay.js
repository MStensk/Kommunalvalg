const MemBodyElement = document.getElementById("party-members-list")

fetch("http://localhost:8080/members")
    .then(response => response.json())
    .then(member => {
        member.map(createMembers);
    });

    function createMembers (member) {
        const memberElement =document.createElement("div");
        memberElement.innerHTML = `
        
        <div class=wrapper id="wrapper">
        <p class="member-name">${member.memberName}</p>
        <p class="member-party">${member.memberParty}</p>
        <p>Stemmer: ${member.voteCount}</p>
        </div>
        
        
       `;

        MemBodyElement.appendChild(memberElement);
    }