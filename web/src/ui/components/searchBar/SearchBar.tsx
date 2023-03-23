import React, {useEffect, useState} from 'react';
import {colors} from "@/ui/layout/styles/Globals";
import styled from "styled-components";
import SecondaryButton from "@/ui/components/buttons/SecondaryButton";
import {useRouter} from "next/router";

function SearchBar({current, users, follow, unfollow}) {
    const [searchText, setSearchText] = useState('');
    const [filteredUsers, setFilteredUsers] = useState(users);
    const router = useRouter()

    useEffect(() => {
        setFilteredUsers(
            users.filter((user) =>
                user.name.toLowerCase().includes(searchText.toLowerCase())
            )
        );
    }, [searchText, users]);

    const handleSearchTextChange = (event) => {
        setSearchText(event.target.value);
    };

    const handleClick = (user) => {
        if (user.isFollowee == false) follow(user.name)
        else unfollow(user.name)
        setSearchText('');
    }

    return (
        <div>
            <StyledSearchBar
                type="text"
                placeholder="Search Twitter"
                value={searchText}
                onChange={handleSearchTextChange}
            />
            <UserList
                style={
                    searchText.length > 0
                        ? {display: 'block'}
                        : {display: 'none'}
                }
            >
                {filteredUsers.map((user) => (
                    current == user.name ? null :
                        <li key={user.id}>
                            <SecondaryButton
                                value={`${user.isFollowee == false ? 'Follow' : 'Unfollow'} ${user.name}`}
                                onClick={() => handleClick(user)}
                            />
                            <SecondaryButton
                                value={'timeline'}
                                onClick={() => router.push(`/users/${user.name}`)}
                            />
                        </li>
                ))}
            </UserList>
        </div>
    );
}

const StyledSearchBar = styled.input`
  width: 100%;
  height: 40px;
  border-radius: 20px;
  border: 1px solid ${colors.gray};
  padding: 0 20px;
  box-sizing: border-box;
  margin-bottom: 20px;
  font-size: 1.2rem;
  background-color: ${colors.black};
  color: ${colors.white};
`;

const UserList = styled.ul`
  list-style: none;
  padding: 0;
  margin: 0;
  background-color: ${colors.black};
  color: ${colors.white};

  li {
    padding: 5px;
    margin: 10px;
  }
`;


export default SearchBar;