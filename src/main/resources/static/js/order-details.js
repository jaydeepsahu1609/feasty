/**
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
*/


/*
* Increment/Decrement pending item count when + or - is clicked in the order-details page
*/
document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".plus, .minus").forEach(btn => {
        btn.addEventListener("click", () => {
            const itemId = btn.dataset.itemId;
            const delta = parseInt(btn.dataset.delta);
            updateItemCount(itemId, delta);
        });
    });
});

function updateItemCount(itemId, delta) {
    var pendingCountSpan = document.querySelector("#pending-count-"+itemId);
    var pendingCount = parseInt(pendingCountSpan.innerText);
    if(delta < 0 && pendingCount == 0)
        return;

    pendingCountSpan.innerText = pendingCount + delta;

    var pendingInput = document.querySelector("#pending-input-"+itemId);
    pendingInput.value = pendingCount + delta;
}
