// teamMembers.js - Handles search and filter functionality for the Team Members component

(() => {
    const component = document.querySelector('.cmp-teammembers');
    if (!component) return;

    const searchInput = component.querySelector('.cmp-teammembers__search-input');
    const clearBtn = component.querySelector('.cmp-teammembers__search-clear');
    const filterButtons = component.querySelectorAll('.cmp-teammembers__filter-btn');
    const cards = component.querySelectorAll('.cmp-teammembers__card');
    const noResults = component.querySelector('.cmp-teammembers__no-results');

    // Helper: show/hide a card based on predicate
    const updateVisibility = () => {
        const query = searchInput.value.trim().toLowerCase();
        const activeFilter = Array.from(filterButtons).find(b => b.classList.contains('cmp-teammembers__filter-btn--active')).dataset.filter;
        let anyVisible = false;
        cards.forEach(card => {
            const name = card.dataset.memberName?.toLowerCase() || '';
            const role = card.dataset.memberRole?.toLowerCase() || '';
            const matchesSearch = !query || name.includes(query) || role.includes(query);
            const matchesFilter = activeFilter === 'all' || role.includes(activeFilter.toLowerCase());
            const visible = matchesSearch && matchesFilter;
            card.style.display = visible ? '' : 'none';
            anyVisible = anyVisible || visible;
        });
        noResults.style.display = anyVisible ? 'none' : '';
    };

    // Search input events
    if (searchInput) {
        searchInput.addEventListener('input', updateVisibility);
    }
    if (clearBtn) {
        clearBtn.addEventListener('click', () => {
            searchInput.value = '';
            updateVisibility();
        });
    }

    // Filter button events
    filterButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            filterButtons.forEach(b => b.classList.remove('cmp-teammembers__filter-btn--active'));
            btn.classList.add('cmp-teammembers__filter-btn--active');
            updateVisibility();
        });
    });

    // Card toggle (quick view) – simple expand/collapse of bio
    cards.forEach(card => {
        const toggle = card.querySelector('.cmp-teammembers__card-toggle');
        const bio = card.querySelector('.cmp-teammembers__bio');
        if (toggle && bio) {
            toggle.addEventListener('click', () => {
                const expanded = bio.style.display === 'block';
                bio.style.display = expanded ? 'none' : 'block';
                toggle.textContent = expanded ? 'View Profile' : 'Hide Profile';
            });
            // Start with bio hidden
            bio.style.display = 'none';
        }
    });
})();

