import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './utilisateur.reducer';

export const UtilisateurDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const utilisateurEntity = useAppSelector(state => state.utilisateur.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="utilisateurDetailsHeading">
          <Translate contentKey="coopcycleApp.utilisateur.detail.title">Utilisateur</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{utilisateurEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="coopcycleApp.utilisateur.name">Name</Translate>
            </span>
            <UncontrolledTooltip target="name">
              <Translate contentKey="coopcycleApp.utilisateur.help.name" />
            </UncontrolledTooltip>
          </dt>
          <dd>{utilisateurEntity.name}</dd>
          <dt>
            <span id="age">
              <Translate contentKey="coopcycleApp.utilisateur.age">Age</Translate>
            </span>
          </dt>
          <dd>{utilisateurEntity.age}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="coopcycleApp.utilisateur.address">Address</Translate>
            </span>
          </dt>
          <dd>{utilisateurEntity.address}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="coopcycleApp.utilisateur.email">Email</Translate>
            </span>
          </dt>
          <dd>{utilisateurEntity.email}</dd>
          <dt>
            <span id="tel">
              <Translate contentKey="coopcycleApp.utilisateur.tel">Tel</Translate>
            </span>
          </dt>
          <dd>{utilisateurEntity.tel}</dd>
          <dt>
            <span id="role">
              <Translate contentKey="coopcycleApp.utilisateur.role">Role</Translate>
            </span>
          </dt>
          <dd>{utilisateurEntity.role}</dd>
          <dt>
            <Translate contentKey="coopcycleApp.utilisateur.idCoop">Id Coop</Translate>
          </dt>
          <dd>{utilisateurEntity.idCoop ? utilisateurEntity.idCoop.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/utilisateur" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/utilisateur/${utilisateurEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default UtilisateurDetail;
