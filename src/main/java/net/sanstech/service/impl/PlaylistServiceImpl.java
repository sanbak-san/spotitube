package net.sanstech.service.impl;

import net.sanstech.dto.PlaylistDTO;
import net.sanstech.dto.PlaylistSummaryDTO;
import net.sanstech.dto.TokenDTO;
import net.sanstech.persistence.PlaylistDAO;
import net.sanstech.persistence.TokenDAO;
import net.sanstech.service.PlaylistService;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class PlaylistServiceImpl implements PlaylistService {

    @Inject
    private PlaylistDAO playlistDAO;

    @Inject
    private TokenDAO tokenDAO;

    @Override
    public PlaylistSummaryDTO getAllPlaylists(final String token) {
        final TokenDTO tokenDTO = tokenDAO.getToken(token);

        return playlistDAO.getAllPlaylists(tokenDTO);
    }

    @Override
    public PlaylistSummaryDTO deletePlaylist(final String token, final int id) {
        playlistDAO.deletePlaylist(id);
        return getAllPlaylists(token);
    }

    @Override
    public PlaylistSummaryDTO addPlaylist(final String token, final PlaylistDTO playlistDTO) {
        playlistDAO.addPlaylist(playlistDTO);
        return getAllPlaylists(token);
    }
}