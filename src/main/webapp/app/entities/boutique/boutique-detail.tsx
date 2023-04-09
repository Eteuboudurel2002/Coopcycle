import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './boutique.reducer';

export const BoutiqueDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const boutiqueEntity = useAppSelector(state => state.boutique.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="boutiqueDetailsHeading">
          <Translate contentKey="coopcycleApp.boutique.detail.title">Boutique</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{boutiqueEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="coopcycleApp.boutique.name">Name</Translate>
            </span>
          </dt>
          <dd>{boutiqueEntity.name}</dd>
          <dt>
            <span id="activity">
              <Translate contentKey="coopcycleApp.boutique.activity">Activity</Translate>
            </span>
          </dt>
          <dd>{boutiqueEntity.activity}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="coopcycleApp.boutique.description">Description</Translate>
            </span>
          </dt>
          <dd>{boutiqueEntity.description}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="coopcycleApp.boutique.address">Address</Translate>
            </span>
          </dt>
          <dd>{boutiqueEntity.address}</dd>
          <dt>
            <Translate contentKey="coopcycleApp.boutique.proprio">Proprio</Translate>
          </dt>
          <dd>{boutiqueEntity.proprio ? boutiqueEntity.proprio.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/boutique" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/boutique/${boutiqueEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default BoutiqueDetail;
